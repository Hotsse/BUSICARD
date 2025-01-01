# Use the official Nginx image
FROM nginx:alpine

# Set the working directory
WORKDIR /usr/share/nginx/html

# Copy Vite build output (dist) to Nginx's HTML directory
COPY src/main/app/dist /usr/share/nginx/html

# Copy a custom Nginx configuration file, if needed
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 for the container
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]