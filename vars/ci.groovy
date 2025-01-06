def call (){
   node ('workstation1') {
       sh 'env'
       sh "find . | sed -e '1d' | xargs rm -rf"
       if(env.TAG_NAME ==~ ".*")
         env.branchName = env.TAG_NAME
       else {
          env.branchName = env.BRANCH_NAME
       } 
       sh 'env' 
         stage('codecheckout'){
            checkout scmGit(branches: [[name: "${env.branchName}"]],
            extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/awsdevopsom1/import-backend.git']])
         }
         sh 'ls'
         sh 'cat Jenkinsfile'
         stage('codecompile'){}

       if(env.Branch_name == "main") {

         stage('codeBuild'){}
      } else if(env.Branch_name ==~ "PR.*"){
         stage('testcases') {}
         stage('integrationtestcases'){}

      } else if (env.TAG_name ==~ ".*"){
         stage('build'){}
         stage('release'){}
      }

      else {
         stage('testcases'){}
      }
   }
}
