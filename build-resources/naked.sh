#/bin/bash

cd ~/tmp

if [ -d naked ]; then
	rm -rf naked
	mkdir naked
else
	mkdir naked
fi

cd naked

svn checkout http://stroy.googlecode.com/svn/trunk/ stroy
