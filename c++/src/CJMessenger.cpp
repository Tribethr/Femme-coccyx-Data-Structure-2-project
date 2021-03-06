#include "CJMessenger.h"

CJMessenger::CJMessenger()
{
    //ctor
    server_fd = 0;
    new_socket = 0;
    valread = 0;
	opt = 1;
	addrlen = sizeof(address);
}

CJMessenger::~CJMessenger()
{
    //dtor
}
void CJMessenger::waitForConection(){
    std::cout<<"Waiting for conection..."<<std::endl;
	// Creating socket file descriptor
	if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
	{
		perror("socket failed");
		exit(EXIT_FAILURE);
	}

	// Forcefully attaching socket to the port 8080
	if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,
												&opt, sizeof(opt)))
	{
		perror("setsockopt");
		exit(EXIT_FAILURE);
	}
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons( PORT );

	// Forcefully attaching socket to the port 8080
	if (bind(server_fd, (struct sockaddr *)&address,
								sizeof(address))<0)
	{
		perror("bind failed");
		exit(EXIT_FAILURE);
	}
	if (listen(server_fd, 3) < 0)
	{
		perror("listen");
		exit(EXIT_FAILURE);
	}
	if ((new_socket = accept(server_fd, (struct sockaddr *)&address,
					(socklen_t*)&addrlen))<0)
	{
		perror("accept");
		exit(EXIT_FAILURE);
	}
}

void CJMessenger::sendData(int * pData){
    send(new_socket , pData, sizeof(int), 0 );
}

void CJMessenger::waitForData(int* pVariable){
    read(new_socket , pVariable, sizeof(int));
}

