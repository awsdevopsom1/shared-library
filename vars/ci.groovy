def call (){
   node ('workstation1') {
       sh 'env'
       sh "find . | sed -e '1d' | xargs rm -rf"
       if(env.TAG_NAME ==~ ".*"){
         env.branchName = "${env.TAG_NAME}" }
       else if(env.BRANCH_NAME ==~ "PR-.*"){
         env.branchName = "${env.CHANGE_BRANCH}"
       }   
       else {
          env.branchName = "${env.BRANCH_NAME}"
       } 
       sh 'env' 
         stage('codecheckout'){
            checkout scmGit(branches: [[name: "${env.branchName}"]],
            extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/awsdevopsom1/import-backend.git']])
         }
        
         sh 'cat Jenkinsfile'
         if(env.app_type == "nodejs")
         stage('Download Dependcies'){
            sh 'npm install'
         }

       if(env.Branch_name == "main") {
         sh 'echo main'
         stage('codeBuild'){}
      } else if(env.Branch_name ==~ "PR.*"){
         sh 'echo PR'
         stage('testcases') {}
         stage('integrationtestcases'){}
       
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
