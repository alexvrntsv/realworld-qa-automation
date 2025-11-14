# Builder
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# Runtime
FROM nginx:1.27
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80