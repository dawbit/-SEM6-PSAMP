#include <iostream>
#include <cmath>

#define M_PI 3.14159265358979323846

template <class A>
class DExpr {
private: A a_;
public: DExpr() {}
		DExpr(const A& x) : a_(x) {}
		double operator()(double x) const {
			return a_(x);
		}
};

class DExprLiteral {
private: double value_; public: DExprLiteral(double value) {
	value_ = value;
}
		 double operator()(double x) const {
			 return value_;
		 }
};

template<class Expr>
void evaluate(DExpr <Expr> expr, double start, double end)
{
	const double step = 1.0;
	for (double i = start; i < end; i += step) {
		std::cout << expr(i) << std::endl;
	}
};

class DExprIdentity {
public: DExprIdentity() {}
		double operator()(double x) const {
			return x;
		}
};

template <class A, class B, class Op>
class DBinExprOp {
	A a_;
	B b_;
public: DBinExprOp(const A& a, const B& b) : a_(a), b_(b) {}
		double operator()(double x) const {
			return Op::apply(a_(x), b_(x));
		}
};

class DApAdd {
public: DApAdd() {}
		static inline double apply(double a, double b) {
			return a + b;
		}
};

class DApMinus {
public: DApMinus() {}
		static inline double apply(double a, double b) {
			return a - b;
		}
};

class DApDivide {
public: DApDivide() {}
		static inline double apply(double a, double b) {
			return a / b;
		}
};

class DApSqrt {
public: DApSqrt() {}
		static inline double apply(double a, double xd) {
			return sqrt(a); // tu powinien byc jeden argument
		}
};

class DApExp {
public: DApExp() {}
		static inline double apply(double a, double xd) {
			return exp(a); // tu powinien byc jeden argument
		}
};

class DApMul {
public: DApMul() {}
		static inline double apply(double a, double b) {
			return a * b;
		}
};

// operator+(double, DExpr)
template <class A>
DExpr<DBinExprOp<DExprLiteral, DExpr<A>, DApAdd>>
operator+(double x, const DExpr<A>& a) {
	typedef DBinExprOp <DExprLiteral, DExpr<A>, DApAdd> ExprT;
	return DExpr<ExprT>(ExprT(DExprLiteral(x), a));
}

// operator/(DExpr, DExpr)
template <class A, class B>
DExpr<DBinExprOp<DExpr<A>, DExpr <B>, DApDivide>>
operator/ (const DExpr <A>& a, const DExpr <B>& b) {
	typedef DBinExprOp <DExpr<A>, DExpr <B>, DApDivide> ExprT;
	return DExpr < ExprT >(ExprT(a, b));
}

// operator/(DExpr, double)
template <class A>
DExpr<DBinExprOp<DExpr<A>, DExprLiteral, DApDivide>>
operator/ (const DExpr <A>& a, double x) {
	typedef DBinExprOp <DExpr<A>, DExprLiteral, DApDivide> ExprT;
	return DExpr < ExprT >(ExprT(a, DExprLiteral(x)));
}

// operator*(double, DExpr)
template <class A>
DExpr<DBinExprOp<DExprLiteral, DExpr<A>, DApMul>>
operator* (double x, const DExpr <A>& a) {
	typedef DBinExprOp <DExprLiteral, DExpr<A>, DApMul> ExprT;
	return DExpr < ExprT >(ExprT(DExprLiteral(x), a));
}


// operator-(DExpr, double)
template <class A>
DExpr<DBinExprOp<DExpr<A>, DExprLiteral, DApMinus>>
operator-(const DExpr<A>& a, double x) {
	typedef DBinExprOp <DExpr<A>, DExprLiteral, DApMinus> ExprT;
	return DExpr<ExprT>(ExprT(a, DExprLiteral(x)));
}

// sqrt(DExpr)
template <class A>
DExpr<DBinExprOp<DExpr<A>, DExprLiteral, DApSqrt>>
sqrt(const DExpr<A>& a) {
	typedef DBinExprOp <DExpr<A>, DExprLiteral, DApSqrt> ExprT;
	return DExpr < ExprT >(ExprT(a, DExprLiteral(0)));
}

// exp(DExpr)
template <class A>
DExpr<DBinExprOp<DExpr<A>, DExprLiteral, DApExp>>
exp(const DExpr<A>& a) {
	typedef DBinExprOp <DExpr<A>, DExprLiteral, DApExp> ExprT;
	return DExpr < ExprT >(ExprT(a, DExprLiteral(0)));
}

int main()
{
	DExpr<DExprIdentity> x;
	//evaluate(x / (1.0 + x), 0.0, 10.0);

	std::cout << "Challenge:\n";
	double mean = 5.0, sigma = 2.0;
	evaluate(1.0 / (sqrt(2 * M_PI) * sigma) * exp(sqrt(x - mean) / (-2 * sigma * sigma)), 0.0, 10.0);

}