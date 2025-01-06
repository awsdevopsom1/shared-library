def call (){
   node ('workstation1') {
       sh 'env'
       if(env.TAG_name ==~ ".*")
         env.branchName = env.TAG_name
       else {
          env.branchName = env.Branch_name
       }  
         stage('codecheckout'){
            checkout scmGit(branches: [[name: "${env.branchName}"]],
            extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/awsdevopsom1/import-backend.git']])
         }
         sh 'ls'
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
