import React, { createContext, useState, useContext, useEffect } from 'react';

const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const [cartItems, setCartItems] = useState([]);

    // Initialize from local storage
    useEffect(() => {
        const savedCart = localStorage.getItem('cart');
        if (savedCart) {
            setCartItems(JSON.parse(savedCart));
        }
    }, []);

    // Save to local storage
    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cartItems));
    }, [cartItems]);

    const addToCart = (product, quantity = 1) => {
        setCartItems(prev => {
            const existingItem = prev.find(item => item.id === product.id);
            if (existingItem) {
                const totalQty = existingItem.quantity + quantity;
                if (totalQty > (product.stock || 100)) {
                    alert(`Sorry! Only ${product.stock} units available in stock.`);
                    return prev;
                }
                return prev.map(item => 
                    item.id === product.id 
                    ? { ...item, quantity: totalQty } 
                    : item
                );
            }
            if (quantity > (product.stock || 100)) {
                alert(`Sorry! Only ${product.stock} units available in stock.`);
                return prev;
            }
            return [...prev, { ...product, quantity }];
        });
    };

    const removeFromCart = (productId) => {
        setCartItems(prev => prev.filter(item => item.id !== productId));
    };

    const updateQuantity = (productId, delta) => {
        setCartItems(prev => {
            return prev.map(item => {
                if (item.id === productId) {
                    const newQty = item.quantity + delta;
                    if (newQty > (item.stock || 100)) {
                        alert(`Sorry! Only ${item.stock} units available in stock.`);
                        return item;
                    }
                    return { ...item, quantity: Math.max(1, newQty) };
                }
                return item;
            });
        });
    };

    const clearCart = () => {
        setCartItems([]);
    };

    const cartTotal = cartItems.reduce((acc, item) => acc + (item.price * item.quantity), 0);

    return (
        <CartContext.Provider value={{ 
            cartItems, 
            addToCart, 
            removeFromCart, 
            updateQuantity,
            clearCart, 
            cartTotal,
            itemCount: cartItems.length 
        }}>
            {children}
        </CartContext.Provider>
    );
};

export const useCart = () => useContext(CartContext);
