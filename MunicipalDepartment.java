import java.util.ArrayList;
import java.util.Scanner;

class Department {
    String name;
    ArrayList<Department> subDepartments;

    public Department(String name) {
        this.name = name;
        this.subDepartments = new ArrayList<>();
    }

    public void addSubDepartment(Department department) {
        subDepartments.add(department);
    }

    public void displayHierarchy(String prefix) {
        System.out.println(prefix + name);
        for (Department subDepartment : subDepartments) {
            subDepartment.displayHierarchy(prefix + "    ");
        }
    }

    public Department findDepartment(String name) {
        if (this.name.equalsIgnoreCase(name)) {
            return this;
        }
        for (Department subDepartment : subDepartments) {
            Department found = subDepartment.findDepartment(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}

public class MunicipalDepartment {
    public static void main(String[] args) {
        Department root = new Department("Municipal Department");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Municipal Department Hierarchy Manager!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Sub-Department");
            System.out.println("2. Display Hierarchy");
            System.out.println("3. Search Department");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter parent department name: ");
                    String parentName = scanner.nextLine();
                    Department parentDepartment = root.findDepartment(parentName);

                    if (parentDepartment != null) {
                        System.out.print("Enter new sub-department name: ");
                        String subDepartmentName = scanner.nextLine();
                        parentDepartment.addSubDepartment(new Department(subDepartmentName));
                        System.out.println("Sub-department added successfully!");
                    } else {
                        System.out.println("Parent department not found.");
                    }
                    break;
                case 2:
                    System.out.println("\nMunicipal Department Hierarchy:");
                    root.displayHierarchy("");
                    break;
                case 3:
                    System.out.print("Enter department name to search: ");
                    String searchName = scanner.nextLine();
                    Department foundDepartment = root.findDepartment(searchName);

                    if (foundDepartment != null) {
                        System.out.println("Department found: " + foundDepartment.name);
                    } else {
                        System.out.println("Department not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting Municipal Department Manager. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
