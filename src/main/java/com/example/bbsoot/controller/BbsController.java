package com.example.bbsoot.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.bbsoot.model.Toukou;
import com.example.bbsoot.model.ToukouRepository;
import com.example.bbsoot.model.User;
import com.example.bbsoot.model.UserRepository;

@Controller
public class BbsController {
	@Autowired
	private ToukouRepository trepository;

	@Autowired
	private UserRepository urepository;

	@Autowired
	private HttpSession session;


	@GetMapping("/")
	public ModelAndView index(ModelAndView mav) {
		List<Toukou> list = trepository.findAll(Sort.by(Direction.DESC,"hi"));

		mav.addObject("list",list);

		mav.setViewName("bbs");
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute User user,ModelAndView mav) {
		//Optional<User> u = urepository.findByUnameAndPass(user.getUname(),user.getPass());
		Optional<User> u = urepository.login(user.getUname(),user.getPass());
		if( u.isPresent()==false) {
			mav.addObject("title","ログイン失敗");
			mav.addObject("mes","ユーザ名またはパスワードが違います");
			mav.addObject("url","/");
			mav.setViewName("mes");
		}else {
			session.setAttribute("user",u.get());
			mav.setViewName("redirect:/");
		}

		return mav;
	}

	@GetMapping("/logout")
	public ModelAndView logout(ModelAndView mav) {
		session.removeAttribute("user");
		session.invalidate();
		mav.setViewName("redirect:/");

		return mav;
	}

	@PostMapping("/write")
	public ModelAndView write(@ModelAttribute Toukou toukou,ModelAndView mav) {
		if( toukou.getBody().length()==0) {
			mav.addObject("title","エラー");
			mav.addObject("mes","何か内容を入力してください");
			mav.addObject("url","/");
			mav.setViewName("mes");
		}else {
			session.setAttribute("toukou",toukou);
			mav.setViewName("write");
		}

		return mav;
	}

	@PostMapping("/writeDone")
	public ModelAndView writeDone(ModelAndView mav) {
		Toukou toukou = (Toukou)session.getAttribute("toukou");
		trepository.save(toukou);
		session.removeAttribute("toukou");
		mav.setViewName("redirect:/");

		return mav;
	}

	@GetMapping("/del/{tid}")
	public ModelAndView del(
			@PathVariable int tid,
			ModelAndView mav) {

		Optional<Toukou> t = trepository.findById(tid);
		Toukou toukou = t.get();

		User user = (User)session.getAttribute("user");
		if( user.getUid() == toukou.getUid()) {
			session.setAttribute("toukou",toukou);
			mav.setViewName("del");
		}else {
			mav.setViewName("redirect:/");
		}


		return mav;
	}

	@PostMapping("/delDone")
	public ModelAndView delDone(ModelAndView mav) {
		Toukou toukou = (Toukou)session.getAttribute("toukou");

		User user = (User)session.getAttribute("user");
		if( user.getUid() == toukou.getUid()) {
			trepository.delete(toukou);
			session.removeAttribute("toukou");
		}

		mav.setViewName("redirect:/");

		return mav;
	}
}
