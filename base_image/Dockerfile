FROM jenkins/jenkins:latest

COPY --chown=jenkins:jenkins plugins.txt "${REF}"/plugins.txt

RUN jenkins-plugin-cli --plugin-file "${REF}"/plugins.txt