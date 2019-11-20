package com.example.demo.controller;

import java.text.ParseException;
import java.util.*;

import com.example.demo.engine.Sender;
import com.example.demo.model.PayLoad;
import com.example.demo.model.Ranking;
import com.example.demo.model.Vote;
import com.example.demo.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rankings")
public class RankingController {
	private final Sender producer;

	@Autowired
	RankingRepository rankingRepository;

	@Autowired
	RankingController(Sender producer) {
		this.producer = producer;
	}

	@PostMapping("/addVote")
	public Ranking addVote(@RequestBody PayLoad payLoad) throws ParseException {
		Ranking ranking = null;
		boolean exist = false;
		exist = rankingRepository.findById(payLoad.getRest_id()).isPresent();
		Date date1 = new Date();
		if (!exist) {
			ranking = new Ranking(payLoad.getRest_id(), new ArrayList<>());
		} else {
			ranking = rankingRepository.findById(payLoad.getRest_id()).get();
		}

		Vote vote = new Vote(payLoad.getUserid(), date1, payLoad.getVote());
		ranking.getVotes().add(vote);
		rankingRepository.save(ranking);
		producer.send(payLoad);
		return ranking;
	}

	@GetMapping("/rankes")
	public List<Ranking> getranking() {
		Iterable<Ranking> result = rankingRepository.findAll();
		List<Ranking> rankList = new ArrayList<Ranking>();
		result.forEach(rankList::add);
		return rankList;
	}

}
