package com.yoshihide.springboot;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yoshihide.springboot.mapper.MyDataMapper;

@Controller
public class HelloController {

	// MyBatis
	@Autowired
	MyDataMapper myDataMapper;

	// 登録フォーム
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("name") String name, @ModelAttribute("mail") String mail,
			@ModelAttribute("age") int age, @Validated MyData mydata, BindingResult result, ModelAndView mov) {
		ModelAndView res = null;
		if (result.hasErrors()) {
			mov.setViewName("index");
			mov.addObject("msg", "正しく入力してください！");
			Iterable<MyData> list = myDataMapper.findAll();
			mov.addObject("datalist", list);
			res = mov;
		} else {
			myDataMapper.save(name, mail, age);
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	// データの取得
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "User List");
		Iterable<MyData> list = myDataMapper.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	// 編集
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "Edit Page");
		Optional<MyData> data = myDataMapper.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute MyData mydata, ModelAndView mav) {
		System.out.println("update");
		myDataMapper.update(mydata);
		return new ModelAndView("redirect:/");
	}

	// 削除
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "Delete Page");
		Optional<MyData> data = myDataMapper.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
		myDataMapper.deleteById(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView searchName(ModelAndView mav, @ModelAttribute String name) {
		mav.setViewName("find");
		mav.addObject("title", "検索結果");
		List<MyData> data = myDataMapper.findByName(name);
		mav.addObject("datalist", data);
		System.out.println(data);
		return mav;

	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
//		mav.setViewName("find");
		String param = request.getParameter("find");
		System.out.println(param);
		List<MyData> result = myDataMapper.findByName(param);
		mav.addObject("datalist", result);
		return mav;

	}

}
