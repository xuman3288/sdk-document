TARGETS = demo
OBJS = demo.o CryptHelper.o
LIBS = -lcrypto


$(TARGETS): $(OBJS)
	rm -f $@
	g++ -o $@ $(OBJS) $(LIBS)

clean :
	rm -f *.o $(TARGETS)
