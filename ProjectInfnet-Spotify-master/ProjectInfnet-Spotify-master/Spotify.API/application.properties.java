# Configuração de logging
logging.level.root=INFO
logging.level.org.springframework=INFO
logging.level.org.hibernate=INFO

#Configuração de hosts permitidos
server.servlet.context-path=/
server.tomcat.remote_ip_header=x-forwarded-for
server.tomcat.protocol_header=x-forwarded-proto
server.tomcat.internal-proxies=.* # Define as configurações necessÃ¡rias para permitir todos os hosts
