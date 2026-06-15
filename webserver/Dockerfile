FROM eclipse-temurin:23-jdk

# Install NGINX, SSH, and Git
RUN apt-get update && apt-get install -y \
    nginx \
    openssh-server \
    git \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Configure SSH
RUN mkdir /var/run/sshd
RUN echo 'root:Hello@123' | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# Copy NGINX configuration
COPY nginx.conf /etc/nginx/sites-available/default

# Copy entrypoint script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Set working directory
WORKDIR /app

# Expose ports
EXPOSE 80 8080 22

# Start the application
ENTRYPOINT ["/entrypoint.sh"]
