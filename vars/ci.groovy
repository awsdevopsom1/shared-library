def call (){
   node {
       sh 'env'

       if(env.Branch_name == "main") {
         stage('codecheckout'){}
         stage('codecompile'){}
         stage('codeBuild'){}
      } else if(env.Branch_name ==~ "PR.*"){
         stage('codecheckout'){}
         stage('codecompile'){}
         stage('testcases') {}
         stage('integrationtestcases'){}

      } else if (env.TAG_name ==~ ".*"){
         stage('codecheckout'){}
         stage('codecompile'){}
         stage('build'){}
         stage('release'){}
      }

      else {
         stage('codecheckout'){}
         stage('codecompile'){}
         stage('testcases'){}
      }
   }
}
