#!/bin/sh

# @author: ankit.a.kumar@oracle.com

## shell script to do dig and ping
## hosts

# set -e

HOSTNAME=$(hostname)
PORT=8080

# very loose regex to check if IP address
# is valid or not.
hosts=$(nslookup $HOSTNAME \
       | grep Address \
       | grep -E '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$' \
       | cut -d: -f2)

echo "the neighbours are..."
echo "$hosts"

for host in $hosts; do
    echo "curling on host $host..."
    echo "============="
    curl http://$host:$PORT --noproxy $host -I
    echo "============="
done
