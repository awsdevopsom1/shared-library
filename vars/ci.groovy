def call () {
  node ('workstation1') {
    sh 'env'
    if(env.BRANCH_NAME == "main"){
     stage('code checkout'){}
     stage('compile'){}
     stage('Build'){}
    } else if(env.BRANCH_NAME ==~ "PR.*"){
       stage('code checkout'){}
       stage('compile'){}
       stage('Test cases') {}
       stage('integration Test cases') {} 
   } else if(env.TAG_NAME ==~ ".*"){
      stage('code checkout'){}
      stage('compile'){}
      stage('Build'){}
      stage('release')
     }
     
      else {
        stage('code checkout'){}
        stage('compile'){}
        stage('Test cases') {}
  }




    // stage('test cases'){}
    // stage('integration test cases')
   
    // stage('Release_App'){}
    }

  

}