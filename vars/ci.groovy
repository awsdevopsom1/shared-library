def call (){
   node {
       sh 'env'

       if(env.Branch_name == "main") {
         stage('codecheckout'){}
         stage('codecompile'){}
         stage('codeBuild'){}
      }

      else {
         stage('codecheckout'){}
         stage('codecompile'){}
         stage('testcases'){}
      }
   }
}
