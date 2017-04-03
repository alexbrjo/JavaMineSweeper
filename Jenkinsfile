pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
    }
    
  }
  stages {
    stage('build') {
      steps {
        fileExists 'Dockerfile'
        sh 'mvn compile'
      }
    }
    stage('test') {
      steps {
        sh 'test'
      }
    }
  }
}