package dev.ananth.kafkaui.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTopicRequest {

      public String name;
      public Integer numPartitions;
      public Short replicationFactor;
}
