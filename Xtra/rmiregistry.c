#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {

	if (chdir("PATH_TO\\src") != 0) {
        perror("chdir failed");
        exit(EXIT_FAILURE);
    }
    
    system("rmiregistry 13190");

    return 0;
}
