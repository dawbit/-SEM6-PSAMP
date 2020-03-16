#include <iostream>
#include <array>

constexpr int TABLE_SIZE = 10;
/**
 * Variadic template for a recursive helper struct.
 */
template<int INDEX = 0, int ...D>
struct Helper : Helper<INDEX + 1, D..., INDEX * INDEX> { };
/**
 * Specialization of the template to end the recursion when the table
size reaches TABLE_SIZE.
 */
template<int ...D>
struct Helper<TABLE_SIZE, D...> {
	static constexpr std::array<int, TABLE_SIZE> table = { D... };
};
constexpr std::array<int, TABLE_SIZE> table = Helper<>::table;
enum {
	FOUR = table[2] // compile time use
};


int main() {
	for (int i = 0; i < TABLE_SIZE; i++) {
		std::cout << table[i] << std::endl;
	}
	std::cout << "FOUR: " << FOUR << std::endl;
}