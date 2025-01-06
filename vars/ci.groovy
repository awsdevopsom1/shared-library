def AWS_SSM_PARAM(param_name) {
  def OUTPUT = sh ( script: "aws ssm get-parameter --name ${param_name} --with-decryption --query 'Parameter.Value' --output text", returnStdout: true).trim()
  return(OUTPUT)
}

 AWS_SSM_PARAM(para)
 def call (){
   node ('workstation1') {
       sh "find . | sed -e '1d' | xargs rm -rf"
       if(env.TAG_NAME ==~ ".*"){
         env.branchName = "${env.TAG_NAME}" }
       else if(env.BRANCH_NAME ==~ "PR-.*"){
         env.branchName = "${env.CHANGE_BRANCH}"
       }   
       else {
          env.branchName = "${env.BRANCH_NAME}"
       } 
       
         stage('codecheckout'){
            checkout scmGit(branches: [[name: "${env.branchName}"]],
            extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/awsdevopsom1/import-backend.git']])
         }
        
         sh 'cat Jenkinsfile'
         if(app_type == "nodejs"){
         stage('Download Dependcies'){
            sh 'npm install'
         }
         }

       if(env.Branch_name == "main") {
         sh 'echo main'
         stage('codeBuild'){}
      } else if(env.Branch_name ==~ "PR.*"){
         sh 'echo PR'
        stage('testcases') {}
        stage('Code Quality') {
        env.SONAR_TOKEN = AWS_SSM_PARAM('sonar.token')
        wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${SONAR_TOKEN}", var: 'PASSWORD']]]) {
          sh 'sonar-scanner -Dsonar.host.url=http://172.31.80.177:9000 -Dsonar.login=${SONAR_TOKEN} -Dsonar.projectKey=import-backend -Dsonar.qualitygate.wait=true -Dsonar.exclusions=node_modules/**'
        }
      }
       
      } else if (env.TAG_name ==~ ".*"){
         sh 'echo TAG'
         stage('build'){}
         stage('release'){}
      }

      else {
         sh 'echo branch'
         stage('testcases'){}
      }
   }
}
