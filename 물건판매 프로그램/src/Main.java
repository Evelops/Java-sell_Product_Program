
import java.io.*;
import java.util.*;

public class Main {
	Scanner scan = new Scanner(System.in);
	StringBuffer s = new StringBuffer(190);
	FileOutputStream out;
	Main[] productList = null;
	int productNum[] = new int[6];
	// ��ǰ�̰����� ���� ������.
	String name;
	int price;
	int count = 0;
	int procount;

	// ��ǰ�� ����
	int newCount = 0;
	int newNumCount = 0;

	// ��ǰ����� ���� ������
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
			System.out.println("========���� �޴�========");
			System.out.println("1. ��ǰ�Ǹ�");
			System.out.println("2. ������");
			System.out.println("3. ����");
			System.out.println("======================\n");
			System.out.println("� ������ �Ͻðڽ��ϱ�?");
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
			System.out.println("\n========��� ����========");
			System.out.println("1. �����ȸ");
			System.out.println("2. ��ǰ�԰�");
			System.out.println("3. ��ǰ��ǰ");
			System.out.println("4. �����޴�");
			System.out.println("======================\n");
			System.out.println("� ������ �Ͻðڽ��ϱ�?  ");
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
			System.out.println((i + 1) + ". " + "��ǰ��: " + productList[i].name + "\t\t����: " + productNum[i]);
		}

	}

	void plusProductNumber() {
		Print();
		System.out.println("\n�԰��Ͻ� ��ǰ ��ȣ�� �ۼ����ּ���: ");
		int kwd = scan.nextInt();
		System.out.println("�԰��Ͻ� ��ǰ ������ �ۼ����ּ���: ");
		int n = scan.nextInt();
		System.out.println("\n");
		productNum[kwd - 1] += n;
		System.out.println("�԰��Ͻ� " + productList[kwd - 1].name + " ��ǰ�� ������ " + productNum[kwd - 1] + "���Դϴ�.");

	}

	void deleteProductNumber() {
		Print();
		System.out.println("\n��ǰ�Ͻ� ��ǰ ��ȣ�� �ۼ����ּ���: ");
		int kwd = scan.nextInt();
		System.out.println("��ǰ�Ͻ� ��ǰ ������ �ۼ����ּ���: ");
		int num = scan.nextInt();
		System.out.println("\n");
		productNum[kwd - 1] -= num;
		System.out.println("��ǰ�Ͻ� " + productList[kwd - 1].name + " ��ǰ�� ������ " + productNum[kwd - 1] + "���Դϴ�.");

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
		System.out.println("�����Ͻ� �ѱݾ��� " + fullcount + "�� �Դϴ�.");
		newBill = fullcount;
		while (true) {
			System.out.println("�����ݾ��� �Ա����ּ���!");
			System.out.println("�����ݾ�:");
			int Bill = scan.nextInt();
			amountReceived += Bill;
			if (newBill - Bill > 0) {
				System.out.println("�����ݾ׿��� " + (newBill - Bill) + "���� �����մϴ�.");
				newBill -= Bill;
			} else
				break;
		}
		System.out.println("======== �� �� �� ========");
		for (int i = 0; i < newCount; i++)
			if (productList[i].count > 0) {
				System.out.println(productList[i].name + "\t" + productList[i].count + "��" + "\t"
						+ (productList[i].count) * (productList[i].price) + "��");
			}
		System.out.println("======================");
		System.out.println("�ѱ��űݾ� \t\t" + fullcount + "��");
		System.out.println("�����ݾ� \t\t" + amountReceived + "��");
		System.out.println("�Ž�����\t\t" + (amountReceived - fullcount) + "��\n\n\n");
	}

	void Print() {
		System.out.println("======== ��ǰ �޴� ========");
		for (int i = 0; i < newCount; i++) {
			System.out.printf("%d", i + 1);
			System.out.printf(".%s\t\t%d��\n", productList[i].name, productList[i].price);
		}
	}

	void firstCount() {
		while (productNum[id] > 0) {
			System.out.println("���ǰ�� �����Ͻðڽ��ϱ�?");
			id = scan.nextInt();
			id--;
			System.out.println(productList[id].name + "�� �����ϼ̽��ϴ�.");
			if (productNum[id] <= 0) {
				System.out.println("�����Ͻ� ��ǰ�� ��� �����ϴ�. �ٽ� �������ּ���.");
				break;
			}
			System.out.println("�ܰ��� " + productList[id].price + "�� �Դϴ�.");
			System.out.println("��� �����Ͻðڽ��ϱ�?");
			productCount = scan.nextInt();
			System.out.println("�����Ͻ� �ݾ��� " + productList[id].price * productCount + "�� �Դϴ�.");
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
			System.out.println("�� �����Ͻðڽ��ϱ�? (��:Y, �ƴϿ�:N)");
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
