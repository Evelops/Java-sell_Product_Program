
import java.io.*;
import java.util.*;

public class Main {
	Scanner scan = new Scanner(System.in);
	StringBuffer s = new StringBuffer(190);
	FileOutputStream out;
	Main[] productList = null;
	int productNum[] = new int[6];
	// 상품이가지는 고유 변수들.
	String name;
	int price;
	int count = 0;
	int procount;

	// 상품의 갯수
	int newCount = 0;
	int newNumCount = 0;

	// 상품계산을 위한 변수들
	int fullcount = 0;
	int id;
	int productCount;
	int Bill;
	int newBill;
	int amountReceived;

	public static void main(String[] args) {
		Main a = new Main();
		a.Menu();
	}

	public void writeFile() {
		File f = new File("ProductNumber.txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(f);
			for (int i = 0; i < newCount; i++) {
				pw.printf("\n%d", productNum[i]);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	void Menu() {
		readProductNumber();
		readProduct();
		while (true) {
			System.out.println("========메인 메뉴========");
			System.out.println("1. 상품판매");
			System.out.println("2. 재고관리");
			System.out.println("3. 종료");
			System.out.println("======================\n");
			System.out.println("어떤 업무를 하시겠습니까?");
			int n = scan.nextInt();
			switch (n) {
			case 1:
				myMain();
				break;
			case 2:
				InventoryManagment();
				break;
			}
			if (n == 3)
				break;
		}
		writeFile();
	}

	void InventoryManagment() {
		while (true) {
			System.out.println("\n========재고 관리========");
			System.out.println("1. 재고조회");
			System.out.println("2. 상품입고");
			System.out.println("3. 상품반품");
			System.out.println("4. 이전메뉴");
			System.out.println("======================\n");
			System.out.println("어떤 업무를 하시겠습니까?  ");
			int n = scan.nextInt();
			switch (n) {
			case 1:
				printProductNumber();
				break;
			case 2:
				plusProductNumber();
				break;
			case 3:
				deleteProductNumber();
				break;
			}
			if (n == 4)
				break;
		}
	}

	void printProductNumber() {
		for (int i = 0; i < newCount; i++) {
			System.out.println((i + 1) + ". " + "상품명: " + productList[i].name + "\t\t개수: " + productNum[i]);
		}

	}

	void plusProductNumber() {
		Print();
		System.out.println("\n입고하실 상품 번호를 작성해주세요: ");
		int kwd = scan.nextInt();
		System.out.println("입고하실 상품 개수를 작성해주세요: ");
		int n = scan.nextInt();
		System.out.println("\n");
		productNum[kwd - 1] += n;
		System.out.println("입고하신 " + productList[kwd - 1].name + " 상품의 개수는 " + productNum[kwd - 1] + "개입니다.");

	}

	void deleteProductNumber() {
		Print();
		System.out.println("\n반품하실 상품 번호를 작성해주세요: ");
		int kwd = scan.nextInt();
		System.out.println("반품하실 상품 개수를 작성해주세요: ");
		int num = scan.nextInt();
		System.out.println("\n");
		productNum[kwd - 1] -= num;
		System.out.println("반품하신 " + productList[kwd - 1].name + " 상품의 개수는 " + productNum[kwd - 1] + "개입니다.");

	}

	void read(Scanner scan) {
		name = scan.next();
		price = scan.nextInt();
	}

	void readProductNumber() {

		Scanner filein = openFile("ProductNumber.txt");
		filein.nextLine();
		while (filein.hasNext()) {
			procount = filein.nextInt();
			productNum[newNumCount] = procount;
			newNumCount++;
		}
		filein.close();
	}

	void readProduct() {
		Scanner filein = openFile("Product.txt");
		filein.nextLine();
		productList = new Main[20];
		while (filein.hasNext()) {
			productList[newCount] = new Main();
			productList[newCount].read(filein);
			newCount++;
		}
		filein.close();
	}

	void fullPrice() {
		System.out.println("구매하신 총금액은 " + fullcount + "원 입니다.");
		newBill = fullcount;
		while (true) {
			System.out.println("결제금액을 입금해주세요!");
			System.out.println("결제금액:");
			int Bill = scan.nextInt();
			amountReceived += Bill;
			if (newBill - Bill > 0) {
				System.out.println("결제금액에서 " + (newBill - Bill) + "원이 부족합니다.");
				newBill -= Bill;
			} else
				break;
		}
		System.out.println("======== 영 수 증 ========");
		for (int i = 0; i < newCount; i++)
			if (productList[i].count > 0) {
				System.out.println(productList[i].name + "\t" + productList[i].count + "개" + "\t"
						+ (productList[i].count) * (productList[i].price) + "원");
			}
		System.out.println("======================");
		System.out.println("총구매금액 \t\t" + fullcount + "원");
		System.out.println("받은금액 \t\t" + amountReceived + "원");
		System.out.println("거스름돈\t\t" + (amountReceived - fullcount) + "원\n\n\n");
	}

	void Print() {
		System.out.println("======== 상품 메뉴 ========");
		for (int i = 0; i < newCount; i++) {
			System.out.printf("%d", i + 1);
			System.out.printf(".%s\t\t%d원\n", productList[i].name, productList[i].price);
		}
	}

	void firstCount() {
		while (productNum[id] > 0) {
			System.out.println("어떤상품을 구매하시겠습니까?");
			id = scan.nextInt();
			id--;
			System.out.println(productList[id].name + "를 선택하셨습니다.");
			if (productNum[id] <= 0) {
				System.out.println("선택하신 상품의 재고가 없습니다. 다시 선택해주세요.");
				break;
			}
			System.out.println("단가는 " + productList[id].price + "원 입니다.");
			System.out.println("몇개를 구입하시겠습니까?");
			productCount = scan.nextInt();
			System.out.println("구매하신 금액은 " + productList[id].price * productCount + "원 입니다.");
			fullcount += productList[id].price * productCount;
			productList[id].count += productCount;
			productNum[id] -= productCount;
			break;
		}
	}

	Scanner openFile(String filename) {
		File f = new File(filename);
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return s;
	}

	void myMain() {
		while (true) {
			Print();
			firstCount();
			System.out.println("더 구매하시겠습니까? (예:Y, 아니오:N)");
			String kwd = scan.next();
			if (kwd.equals("Y"))
				continue;
			else if (kwd.equals("N")) {
				fullPrice();
				break;
			}
		}
	}
}
