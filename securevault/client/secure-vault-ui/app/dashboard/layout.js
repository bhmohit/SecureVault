import DashboardNav from "@/components/Navbar/DashboardNav"

const DashboardLayout = ({ children }) =>
(
    <div>
        <DashboardNav />
        {children}
    </div>
)

export default DashboardLayout;