FROM nginx:1.13.3-alpine

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY nginx/jenkins.conf /etc/nginx/conf.d/jenkins.conf
