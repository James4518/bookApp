package book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class bookApp {
    static List<book> bookList = new ArrayList<book>();
    static List<book> bookPerson = new ArrayList<book>();   
    static int count = 0;           
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        initBook();
		menu();
		boolean flag = true;
		while(flag){
			if(s.hasNextInt()){
				int a=s.nextInt();
				if(a>0 && a<=4){
					switch(a){
						case 1:{
                            show();
                            while(true){
                                int n=s.nextInt();
                                if(n>0 && n<=count){
                                    book b1=bookList.get(n-1);
                                    bookPerson.add(b1);
                                    System.out.println("你选择的书籍为：" + b1.getName());
                                    }
                                else if(n==0){menu();break;}
                            }  
                        } 
                        case 2:{
                            order();
                            System.out.println("请输入想删除的，没有输入0回到主页");
                            int r=s.nextInt();
                            if(r!=0){
                                r=r-1;
                                for(int i=0;i<=bookPerson.size();i++){
                                    if(i==r){
                                        bookPerson.remove(i);
                                    }
                                }
                            }else{
                                order();
                                menu();
                            }
                            break;
                        }
                        case 3:{
                            totalPrice();
                            break;
                        }
                        case 4:{
                            flag=false;
                            System.out.println("欢迎您的再次光临！");
                            break;
                        }
					}
				}else{
                    System.out.println("请输入正确的编号!");
                }
			}
		}
	}
    
    public static void menu(){       
        System.out.println("------------主菜单------------");
        System.out.println("*书单\t\t\t\t 1");
        System.out.println("*已选书籍\t\t\t 2");
        System.out.println("*买单\t\t\t\t 3");
        System.out.println("*退出\t\t\t\t 4");
        System.out.println("请根据编号选择你想要的服务");
    }
    public static void initBook(){
        bookList.add(new book(1,"Java程序设计",58.00));
        bookList.add(new book(2,"应用软件工程",39.8));
        bookList.add(new book(3,"数据库基础教程",44.6));
        bookList.add(new book(4,"电路",76.4));
        count = bookList.size();
     }
     public static void show(){      
        System.out.println("\n\n****************");
        System.out.println("请选择您心仪的书籍：");
        for (book temp:bookList){
            System.out.println(temp);
        }
        System.out.println("请输入相应的序号，输入9删除书籍，输入0返回上一级");
    }
     private static void order() {
        if(bookPerson.isEmpty()){System.out.println("抱歉，您目前还没有选择任何书籍哦~");}
        else{
            for(book temp:bookPerson){System.out.println("你已选择的书籍有:" + temp);}
            System.out.println("其中相同的是："+getElements(bookPerson.stream()));
            System.out.println("本书有："+getCount(bookPerson.stream()));
            //System.out.println("本数有:"+bookPerson.stream().distinct().collect(Collectors.toList()));
        }
    }
     public static void totalPrice(){
        double pr=0;
        System.out.println("请稍等，正在为你结算~");
        for(book temp:bookPerson){
            pr+= temp.getPrice();
        }
        System.out.println("你本次一共消费" + pr + "元!");
     }
    public static <T> List<T> getElements(Stream<T> stream) {
        return stream
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
    }
    public static <T, C> List<Integer> getCount(Stream<T> stream) {
        return stream
        .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
        .entrySet().stream() // Set<Entry>转换为Stream<Entry>
        .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
        .map(entry -> entry.getValue()) // 获得 entry 的值（重复元素）对应的 Stream
        .collect(Collectors.toList()); // 转化为 List
    }
}
