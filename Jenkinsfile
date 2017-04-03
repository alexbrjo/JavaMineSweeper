pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
    }
    
  }
  stages {
    stage('build') {
      steps {
        sh '''mvn compile
'''
      }
    }
    stage('test') {
      steps {
        sh 'test'
      }
    }
  }
}