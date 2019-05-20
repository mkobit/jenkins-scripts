FROM nginx:1.15.12

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY nginx/jenkins.conf /etc/nginx/conf.d/jenkins.conf
