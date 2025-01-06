def call (){
   node ('workstation1') {
       sh 'env'
       sh "find . | sed -e '1d' | xargs rm -rf"
       if(env.TAG_NAME ==~ ".*")
         env.branchName = env.TAG_NAME
       else {
          env.branchName = env.BRANCH_NAME
       }  
         stage('codecheckout'){
            checkout scmGit(branches: [[name: "${env.branchName}"]],
            extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/awsdevopsom1/"${repo_name}".git']])
         }
         sh 'ls'
         sh 'cat jenkinsfile'
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
