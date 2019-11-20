FROM node:carbon
 
# Create app directory
WORKDIR /usr/src/app
 
COPY package.json .
COPY package-lock.json .
 
RUN npm install
 
COPY . .
 
EXPOSE 3005
 
CMD [ "npm", "start" ]