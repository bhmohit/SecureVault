import MainNav from "@/components/Navbar/MainNav";

export default function HomeLayout({ children }) {
    return (
        <div>
            <MainNav />
            {children}
        </div>
    )
}