$ docker info
$ sudo docker run -it centos:6 /bin/bash
$ docker ps -a
$ docker ps
$ sudo docker run --name mycontainer -it centos:6 /bin/bash
$ docker rm adoring_kare
$ docker start mycontainer
$ docker stop mycontainer
$ sudo docker run --name mycontainer -d centos:6 /bin/bash -c "while true; do echo love@`date`; sleep 1; done"
$ docker logs mycontainer
$ docker logs -f mycontainer
$ docker logs -ft mycontainer
$ docker logs --tail 10 mycontainer
$ docker logs --tail 0 -f mycontainer

