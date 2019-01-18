#ifndef CJMESSENGER_H
#define CJMESSENGER_H

#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <iostream>
#include <thread>
#define PORT 8086

class CJMessenger
{
    public:
        CJMessenger();
        virtual ~CJMessenger();
        void waitForConection();
        void sendData(int* pData);
        void waitForData(int* pVariable);
        void waitLikeThread();

    protected:

    private:
        int server_fd, new_socket, valread;
        struct sockaddr_in address;
        int opt;
        int addrlen;
};

#endif // CJMESSENGER_H
