package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.dto.QnaDto;
import com.ssafy.happyhouse.service.QnaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "크롤링입니다./// 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@GetMapping
	public ResponseEntity<List<NewsDto>> crawling() throws Exception {
		String url = "https://land.naver.com/news/headline.naver";

		Document doc = Jsoup.connect(url).get();
		List<NewsDto> list = new ArrayList<>();
		Element newslist = doc.select(".section_headline .headline_list").get(0);
		Elements newsElements = newslist.select("li");
		int i = 1;
		for (Element e : newsElements) {
			if (i % 6 != 0 && i<=5) {
				NewsDto news = new NewsDto();
				news.setTitle(e.getElementsByTag("a").text());
				news.setPhoto(e.getElementsByAttribute("src").attr("src"));
				news.setUrl(e.getElementsByAttribute("href").attr("href"));
				list.add(news);
			}
			i++;
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("market_trend")
	public ResponseEntity<List<NewsDto>> market_trend_crawling() throws Exception {
		String url = "https://land.naver.com/news/field.naver?news_type_cd=10";

		Document doc = Jsoup.connect(url).get();
		List<NewsDto> list = new ArrayList<>();
		Element newslist = doc.select(".section_headline .headline_list").get(0);
		Elements newsElements = newslist.select("li");
		int i = 1;
		for (Element e : newsElements) {
			if (i % 6 != 0 && i<=5) {
				NewsDto news = new NewsDto();
				news.setTitle(e.getElementsByTag("a").text());
				news.setPhoto(e.getElementsByAttribute("src").attr("src"));
				news.setUrl(e.getElementsByAttribute("href").attr("href"));
				list.add(news);
			}
			i++;
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("policy_system")
	public ResponseEntity<List<NewsDto>> policy_system_crawling() throws Exception {
		String url = "https://land.naver.com/news/field.naver?news_type_cd=20";

		Document doc = Jsoup.connect(url).get();
		List<NewsDto> list = new ArrayList<>();
		Element newslist = doc.select(".section_headline .headline_list").get(0);
		Elements newsElements = newslist.select("li");
		int i = 1;
		for (Element e : newsElements) {
			if (i % 6 != 0 && i<=5) {
				NewsDto news = new NewsDto();
				news.setTitle(e.getElementsByTag("a").text());
				news.setPhoto(e.getElementsByAttribute("src").attr("src"));
				news.setUrl(e.getElementsByAttribute("href").attr("href"));
				list.add(news);
			}
			i++;
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("distribute")
	public ResponseEntity<List<NewsDto>> distribute_crawling() throws Exception {
		String url = "https://land.naver.com/news/field.naver?news_type_cd=40";

		Document doc = Jsoup.connect(url).get();
		List<NewsDto> list = new ArrayList<>();
		Element newslist = doc.select(".section_headline .headline_list").get(0);
		Elements newsElements = newslist.select("li");
		int i = 1;
		for (Element e : newsElements) {
			if (i % 6 != 0 && i<=5) {
				NewsDto news = new NewsDto();
				news.setTitle(e.getElementsByTag("a").text());
				news.setPhoto(e.getElementsByAttribute("src").attr("src"));
				news.setUrl(e.getElementsByAttribute("href").attr("href"));
				list.add(news);
			}
			i++;
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("industry_trend")
	public ResponseEntity<List<NewsDto>> industry_trend_crawling() throws Exception {
		String url = "https://land.naver.com/news/field.naver?news_type_cd=50";

		Document doc = Jsoup.connect(url).get();
		List<NewsDto> list = new ArrayList<>();
		Element newslist = doc.select(".section_headline .headline_list").get(0);
		Elements newsElements = newslist.select("li");
		int i = 1;
		for (Element e : newsElements) {
			if (i % 6 != 0 && i<=5) {
				NewsDto news = new NewsDto();
				news.setTitle(e.getElementsByTag("a").text());
				news.setPhoto(e.getElementsByAttribute("src").attr("src"));
				news.setUrl(e.getElementsByAttribute("href").attr("href"));
				list.add(news);
			}
			i++;
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
}