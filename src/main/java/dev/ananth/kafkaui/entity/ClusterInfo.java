package dev.ananth.kafkaui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClusterInfo {

    String host;
    int totaltopics;
    int totalparitions;

}
