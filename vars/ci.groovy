def call () {
  node ('workstation1') {
    sh 'env'
    if(BRANCH_NAME == "main"){
     stage('code checkout'){}
     stage('compile'){}
     stage('Build'){}
    } else if(BRANCH_NAME ==~ "PR*"){
       stage('code checkout'){}
       stage('compile'){}
       stage('integration Test cases') {} 

     } else {
        stage('code checkout'){}
        stage('compile'){}
        stage('Test cases') {}
  }




    // stage('test cases'){}
    // stage('integration test cases')
   
    // stage('Release_App'){}
    }

  

}