#include "log.h"

void my_log(const char *format, ...) {
	va_list arglist;
	char text[STRLEN];
	va_start(arglist, format);
	vsnprintf(text,STRLEN,format,arglist);
	va_end(arglist);
	LOGE(text);
}
