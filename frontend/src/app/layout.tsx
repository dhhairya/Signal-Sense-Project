import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Link from "next/link";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Signal-Sense",
  description: "AI-powered platform connecting volunteers with community needs.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <nav className="w-full bg-blue-600 text-white p-4 shadow-md flex justify-between items-center">
          <Link href="/">
            <h1 className="text-xl font-bold cursor-pointer">Signal-Sense</h1>
          </Link>
          <div className="space-x-4">
            <Link href="/opportunities" className="hover:underline">Explore</Link>
            <Link href="/login" className="hover:underline">Login</Link>
            <Link href="/register" className="bg-white text-blue-600 px-4 py-2 rounded font-semibold hover:bg-gray-100">Sign Up</Link>
          </div>
        </nav>
        <main className="min-h-screen p-8 bg-gray-50 text-gray-900">
          {children}
        </main>
      </body>
    </html>
  );
}
