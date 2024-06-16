// JUnit 테스트 경로 변수 설정
def classpath = "classes:lib/junit-platform-console-standalone-1.7.1.jar"

pipeline {
    agent any

    environment {
            LC_ALL = 'ko_KR.UTF-8'
            LANG = 'ko_KR.UTF-8'
        }

    stages {
        stage('Checkout') {
            steps {
                // 소스코드 체크아웃
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Java 파일들을 컴파일하여 생성된 클래스 파일을 classes 디렉토리에 저장
                sh 'javac -encoding UTF-8 -classpath ./lib/junit-jupiter-api-5.8.1.jar -d classes main/Book.java main/BookManager.java'
                sh 'javac -encoding UTF-8 -classpath ./lib/junit-jupiter-api-5.8.1.jar:./classes -d classes test/BookManagerTest.java'
            }
        }
        stage('Test') {
            steps {
                // JUnit 5 테스트 실행
                sh "java -Dfile.encoding=UTF-8 -cp ${classpath} org.junit.platform.console.ConsoleLauncher --scan-classpath > testResults.txt"
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'testResults.txt', allowEmptyArchive: true
        }
        failure {
            echo 'Build or test failed'
        }
        success {
            echo 'Build and test succeeded'
        }
    }
}