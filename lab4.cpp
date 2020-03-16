/* File: VectorDriver.cpp
 * Athr: Dimitri Zarzhitsky
 * Date: October 16, 2002
 *
 * Desc: provides the basic framework and examples for an introduction to
 *       the STL::vector, iterators, and the algorithms facilities.
 */
typedef unsigned long ulong;

#include <iostream>
#include <vector>
#include <algorithm>

using std::cout;
using std::endl;
using std::vector;

void fill_vector(vector<short>& data) {
	for (ulong i = 0; i < data.size(); i++) {
		data.at(i) = i;
	}
}

void print(const vector<short>& data) {
	if (data.empty()) {
		cout << "<empty>";
	}
	else {
		cout << "<" << data.at(0);
		for (ulong i = 1; i < data.size(); i++) {
			cout << ", " << data.at(i);
		}
		cout << ">";
	}
}

// Ex. 2
void print_even(const vector<short>& data) {
	if (data.empty()) {
		cout << "<empty>";
	}
	else {
		cout << "<" << data.at(0);
		for (ulong i = 2; i < data.size(); i += 2) {
			cout << ", " << data.at(i);
		}
		cout << ">";
	}
}

short compute_sum(const vector<short>& data) {
	std::vector<short>::const_iterator iter = data.begin();

	short sum = 0;
	while (iter != data.end()) {
		sum += *iter;
		iter++;
	}

	return sum;
}

// Ex. 1
void add_numbers(vector<short>& data) {
	int size = data.size();
	vector<short> temp_vector(size + 10);

	for (ulong i = 0; i < temp_vector.size(); i++) {
		if (i < size) {
			temp_vector.at(i) = data.at(i);
		}
		else {
			temp_vector.at(i) = 0;
		}
	}
	data = temp_vector;
}

// Ex. 3
bool is_present(const vector<short>& data, short value) {
	std::vector<short>::const_iterator iter = data.begin();

	while (iter != data.end()) {
		if (*iter == value) {
			return true;
		}
		iter++;
	}

	return false;
}

int main() {
	cout << "  ..:: B E G I N  S A M P L E  C O D E  ::.." << endl << endl;
	vector<short> sample_vector(5);

	cout << "new vector: ";
	print(sample_vector);
	cout << endl;

	fill_vector(sample_vector);

	cout << "filled vector: ";
	print(sample_vector);
	cout << endl;

	cout << "sum of vector's elements: " << compute_sum(sample_vector) << endl;
	cout << endl << "   ..::  E N D   S A M P L E  C O D E  ::.." << endl;

	// Ex. 1
	vector<short> vector_ex1(5);

	cout << endl << "   ..::  BEGIN TASK 1  ::.." << endl;
	cout << "New vector: ";
	print(vector_ex1);
	cout << endl;
	cout << "Adding 10 numbers to vector: ";
	add_numbers(vector_ex1);
	print(vector_ex1);
	cout << endl;

	cout << "Adding 10 MORE numbers to vector: ";
	add_numbers(vector_ex1);
	print(vector_ex1);
	cout << endl << "   ..::  END TASK 1  ::.." << endl;

	// Ex. 2
	cout << endl << "   ..::  BEGIN TASK 2  ::.." << endl;
	cout << "Print at EVEN indexes [0, 2, ...]: ";
	print_even(sample_vector);
	cout << endl << "   ..::  END TASK 2  ::.." << endl;

	// Ex. 3
	cout << endl << "   ..::  BEGIN TASK 3  ::.." << endl;
	cout << "Vector: ";
	print(sample_vector);
	cout << endl;
	cout << "Find 2 in Vector: ";
	cout << is_present(sample_vector, 2);
	cout << endl;
	cout << "Find 222 in Vector: ";
	cout << is_present(sample_vector, 222);
	cout << endl << "   ..::  END TASK 3  ::.." << endl;

	// Ex. 4
	cout << endl << "   ..::  BEGIN TASK 4  ::.." << endl;
	int totally_random_numbers[] = { 2, 4, 99, 6, 6977 };
	vector<short> ex4_vector(totally_random_numbers, totally_random_numbers + 5);
	cout << "Vector TO sort: ";
	print(ex4_vector);
	cout << endl;

	std::sort(ex4_vector.begin(), ex4_vector.end());

	cout << "Vector AFTER sort: ";
	print(ex4_vector);
	cout << endl << "   ..::  END TASK 4  ::.." << endl;

	return 0;
}