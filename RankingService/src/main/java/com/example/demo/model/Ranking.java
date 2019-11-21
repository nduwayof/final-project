package com.example.demo.model;

import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;


@Table
public class Ranking {
    public String getResturant_id() {
        return resturant_id;
    }

    public void setResturant_id(String resturant_id) {
        this.resturant_id = resturant_id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public Ranking(@NonNull String resturant_id, @NonNull List<Vote> votes) {
        this.resturant_id = resturant_id;
        this.votes = votes;
    }


    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }



    @PrimaryKey
    private @NonNull String resturant_id;
    @Column(value = "votes")
    private @NonNull List<Vote> votes;



}
