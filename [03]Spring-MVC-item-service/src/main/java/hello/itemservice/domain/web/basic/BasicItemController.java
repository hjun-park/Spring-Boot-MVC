package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

	private final ItemRepository itemRepository;

//	@Autowired
//	public BasicItemController(ItemRepository itemRepository) {
//		this.itemRepository = itemRepository;
//	}

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}

	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}

	//	@PostMapping("/add")
	public String addItemV1(@RequestParam String itemName,
							@RequestParam int price,
							@RequestParam Integer quantity,
							Model model) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);

		itemRepository.save(item);

		model.addAttribute("item", item);
		return "basic/item";
	}

//	@PostMapping("/add")
	public String addItemV2(@ModelAttribute("item") Item item) { //, Model model) {
		// >> 04. ModelAttribute의 2가지 기능
		//		1) Item이라는 객체를 생성해주고 프로퍼티 접근으로 item에 집어넣어준다.
		//		2) Model에다가 지정한객체(item)을 자동으로 넣어준다.
		//		그래서 model.addAttribute("item", item) 주석처리해도 되는 이유다.

		// modelAttribute로 입력받으면 setter 쓸 필요가 없음
		//		Item item = new Item();
		//		item.setItemName(itemName);
		//		item.setPrice(price);
		//		item.setQuantity(quantity);

		itemRepository.save(item);

		// model은 생략 가능
		//		model.addAttribute("item", item);
		return "basic/item";
	}


	// >> 05. 상품 등록 후 새로고침하면 계속 등록되는 문제 발생
	// PRG 방식으로 개선 ( 등록된 상품 상세페이지로 이동 )
//	@PostMapping("/add")
	public String addItemV3(@ModelAttribute("item") Item item) { //, Model model) {
		itemRepository.save(item);

		return "redirect:/basic/items/" + item.getId();
	}

	// >> 06. 그냥 보이면 저장이 제대로 됐는지 확인 불가능
	// 클라이언트 배려 측면에서 변경 ( redirectAttributes )
	@PostMapping("/add")
	public String addItemV4(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);

		//  URI에 status = true를 확인할 수 있음
		// return보면 status가 없는데 status는 URI에 알아서 들어간다.
		return "redirect:/basic/items/{itemId}";

	}

	// 상품 수정 폼
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}

	// 상품 수정
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);

		// 저장하고나서 이전 화면으로 이동 (리다이렉트)
		return "redirect:/basic/items/{itemId}";
	}


	// 03. 테스트 전용 데이터 추가
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}


}
