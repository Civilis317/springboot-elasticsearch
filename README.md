# springboot-elasticsearch
- Spring Boot 2.0.3.RELEASE
- Elasticsearch 6.1.3, running external (localhost:9200)

In elasticsearch an index holding bankaccounts can be created by changing directory into the _doc_ folder where the file _accounts.json_ resides.  There run:__  
 ``curl -H "Content-Type: application/json" -XPOST "localhost:9200/bank/_doc/_bulk?pretty&refresh" --data-binary "@accounts.json"  
