#include <iostream>
#include <array>

constexpr int TABLE_SIZE = 20;
constexpr int OFFSET = 12;
/**
 * Template to calculate a single table entry
 */
template <typename VALUETYPE, VALUETYPE OFFSET, VALUETYPE INDEX>
struct ValueHelper {
	static constexpr VALUETYPE value = OFFSET + INDEX * INDEX;
};
/**
 * Variadic template for a recursive helper struct.
 */
template<typename VALUETYPE, VALUETYPE OFFSET, int N = 0, VALUETYPE
	...D>
	struct Helper : Helper<VALUETYPE, OFFSET, N + 1, D...,
	ValueHelper<VALUETYPE, OFFSET, N>::value> { };
/**
 * Specialization of the template to end the recursion when the table
size reaches TABLE_SIZE.
 */
template<typename VALUETYPE, VALUETYPE OFFSET, VALUETYPE ...D>
struct Helper<VALUETYPE, OFFSET, TABLE_SIZE, D...> {
	static constexpr std::array<VALUETYPE, TABLE_SIZE> table = { D... };
};
constexpr std::array<uint16_t, TABLE_SIZE> table = Helper<uint16_t, OFFSET>::table;


int main() {
	// Ex. 2
	for (int i = 0; i < TABLE_SIZE; i++) {
		std::cout << table[i] << std::endl;
	}

}
// https://stackoverflow.com/questions/27744079/is-it-a-conforming-compiler-extension-to-treat-non-constexpr-standard-library-fu/27744080#27744080
