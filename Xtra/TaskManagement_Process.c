#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	system("start \"\" \"PATH_TO\\slave0.exe\"");
	system("start \"\" \"PATH_TO\\slave1.exe\"");
	system("start \"\" \"PATH_TO\\slave2.exe\"");
	system("start \"\" \"PATH_TO\\slave3.exe\"");
	system("start \"\" \"PATH_TO\\rmiregistry.exe\"");
	system("start \"\" \"PATH_TO\\TSServer.exe\"");
	system("start \"\" \"PATH_TO\\TaskManagements.exe\"");
		
		return 0;
}
