package flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @Author zjh
 * @Date 2019/10/31,11:45
 */
public class WordCountDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text = env.socketTextStream("127.0.0.1", 9000, "\n");
        DataStream<WordIsCount> windowCounts = text.flatMap(new FlatMapFunction<String, WordIsCount>() {
            @Override
            public void flatMap(String s, Collector<WordIsCount> collector) throws Exception {
                String[] words = s.split("\\s");
                for(String word : words) {
                    collector.collect(new WordIsCount(word, 1L));
                }
            }
        }).keyBy("word")
          .timeWindow(Time.seconds(2), Time.seconds(1))
          .sum("count");

        windowCounts.print().setParallelism(1);
        env.execute("socket window count");
    }

    public static class WordIsCount{
        public String word;
        public long count;

        public WordIsCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        public WordIsCount() {
        }

        @Override
        public String toString() {
            return "WordIsCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
