### Create topic with 2 partitions

~/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic bar

### Environment variables

bootstrapAddress: PLAINTEXT://localhost:9092

