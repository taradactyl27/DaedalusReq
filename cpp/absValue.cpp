int Abs(int i);
int main()
{
	int number;
	int abs_number;

	std::cout << "This program finds the absolute value of an integer." << endl;
	std::cout << "Enter an integer (positive or negative): ";
	std::cin >> number;
	
	// Calling the function Abs()
	abs_number = Abs(number);
	std::cout << "The absolute value of " << number << " is " << abs_number;
	std::cout << endl;
	return 0;
}
// Function definition
int Abs(int i)
{
	if( i >= 0)
		return i;
	else
		return -i;
}
