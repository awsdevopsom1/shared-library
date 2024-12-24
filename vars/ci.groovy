def call () {
  node ('workstation1') {
    sh 'env'
    if(BRANCH_NAME == "main")
    stage('code checkout'){}
    stage('compile'){}
     stage('Build'){}



    // stage('test cases'){}
    // stage('integration test cases')
   
    // stage('Release_App'){}
    }

}  

