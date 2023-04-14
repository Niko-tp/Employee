package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.common.FlashData;
import com.example.demo.entity.Employee;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Employee/employee")
public class EmployeeController {
	@Autowired
	BaseService<Employee> employeeService;

	/*
	 * 一覧表示
	 */
	@GetMapping(value = "/list")
	public String list(Model model) {
		// 全件取得
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		
		return "employee/list";
	}
	
	/*
	 * 新規追加画面表示
	 */
	@GetMapping(value = "/new")
	public String form(Employee employee, Model model) {
		model.addAttribute("employee", employee);
		
		return "employee/new";
	}

	/*
	 * 登録
	 */
	@PostMapping(value = "/new")
	public String register(@Valid Employee employee, BindingResult result, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				return "employee/new";
			}
			// 登録
			employeeService.save(employee);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		
		return "redirect:/Employee/employee/list";
	}

	/*
	 * 編集画面表示
	 */
	@GetMapping(value = "/edit")
	public String edit(@RequestParam("id") Integer id, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			// レコード取得 & 存在確認
			Employee employee = employeeService.findById(id);
			model.addAttribute("employee", employee);
			
			return "employee/edit";
		} catch (DataNotFoundException e) {
			flash = new FlashData().danger("該当データがありません");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		
		return "redirect:/Employee/employee/list";
	}
	
	/*
	 * 更新
	 */
	@PostMapping(value = "/edit")
	public String update(@RequestParam("id") Integer id, @Valid Employee employee, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				return "employee/edit";
			}
			// 存在確認
			employeeService.findById(id);
			// 更新
			employeeService.save(employee);
			
			flash = new FlashData().success("更新しました");
		} catch (DataNotFoundException e) {
			flash = new FlashData().danger("該当データがありません");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		
		return "redirect:/Employee/employee/list";
	}
	
	/*
	 * 削除
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, RedirectAttributes ra) {
		FlashData flash;
		try {
			// 存在確認
			employeeService.findById(id);
			// 削除
			employeeService.deleteById(id);
			
			flash = new FlashData().success("削除しました");
		} catch (DataNotFoundException e) {
			flash = new FlashData().danger("該当データがありません");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		
		return "redirect:/Employee/employee/list";
	}
}