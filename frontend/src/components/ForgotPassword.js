import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axiosInstance from '../api/axiosConfig';
import { Mail, ShieldCheck, Lock, ArrowLeft, Loader2, Eye, EyeOff } from 'lucide-react';

const ForgotPassword = () => {
  const [step, setStep] = useState(1); // 1: Email, 2: OTP & New Password
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleSendOtp = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      await axiosInstance.post('/auth/forgot-password', { email });
      setMessage('OTP sent to your email!');
      setStep(2);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to send OTP. Is the email registered?');
    } finally {
      setLoading(false);
    }
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      await axiosInstance.post('/auth/reset-password', { email, otp, newPassword });
      alert('Password updated successfully!');
      navigate('/login');
    } catch (err) {
      setError(err.response?.data?.message || 'Reset failed. Check your OTP.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-wrapper" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh', background: '#f0f2f5' }}>
      <div className="auth-card" style={{ width: '450px', background: 'white', borderRadius: '24px', boxShadow: '0 20px 25px -5px rgba(0,0,0,0.1)', overflow: 'hidden' }}>
        <div style={{ padding: '40px' }}>
          <Link to="/login" style={{ display: 'flex', alignItems: 'center', gap: '8px', color: '#64748b', textDecoration: 'none', marginBottom: '24px', fontSize: '14px' }}>
            <ArrowLeft size={16} /> Back to Login
          </Link>

          <h2 style={{ fontSize: '28px', fontWeight: '800', marginBottom: '12px', color: '#1e293b' }}>
            {step === 1 ? 'Forgot Password?' : 'Secure Reset'}
          </h2>
          <p style={{ color: '#64748b', marginBottom: '32px', fontSize: '15px' }}>
            {step === 1 
              ? "No worries! Enter your email and we'll send you a 6-digit code to reset your password."
              : "We've sent a 6-digit code to your email. Please verify it to set your new password."}
          </p>

          {error && <div style={{ background: '#fee2e2', color: '#dc2626', padding: '12px', borderRadius: '12px', marginBottom: '20px', fontSize: '14px', fontWeight: '600' }}>{error}</div>}
          {message && <div style={{ background: '#dcfce7', color: '#16a34a', padding: '12px', borderRadius: '12px', marginBottom: '20px', fontSize: '14px', fontWeight: '600' }}>{message}</div>}

          {step === 1 ? (
            <form onSubmit={handleSendOtp}>
              <div className="form-group" style={{ marginBottom: '24px' }}>
                <label className="form-label">Email Address</label>
                <div style={{ position: 'relative' }}>
                  <Mail style={{ position: 'absolute', left: '14px', top: '50%', transform: 'translateY(-50%)', color: '#94a3b8' }} size={18} />
                  <input
                    type="email"
                    required
                    style={{ width: '100%', padding: '14px 14px 14px 44px', borderRadius: '12px', border: '1.5px solid #e2e8f0', fontSize: '15px' }}
                    placeholder="you@example.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
              </div>
              <button disabled={loading} type="submit" className="auth-btn" style={{ width: '100%', padding: '14px', borderRadius: '12px', background: '#2563eb', color: 'white', border: 'none', fontWeight: '700', cursor: 'pointer', display: 'flex', justifyContent: 'center', alignItems: 'center', gap: '10px' }}>
                {loading ? <Loader2 className="animate-spin" /> : 'Send OTP Code'}
              </button>
            </form>
          ) : (
            <form onSubmit={handleResetPassword}>
              <div className="form-group" style={{ marginBottom: '20px' }}>
                <label className="form-label">Verification Code (OTP)</label>
                <div style={{ position: 'relative' }}>
                  <ShieldCheck style={{ position: 'absolute', left: '14px', top: '50%', transform: 'translateY(-50%)', color: '#94a3b8' }} size={18} />
                  <input
                    type="text"
                    required
                    maxLength="6"
                    style={{ width: '100%', padding: '14px 14px 14px 44px', borderRadius: '12px', border: '1.5px solid #e2e8f0', fontSize: '15px', letterSpacing: '4px', fontWeight: 'bold' }}
                    placeholder="000000"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                  />
                </div>
              </div>
              <div className="form-group" style={{ marginBottom: '24px' }}>
                <label className="form-label">New Password</label>
                <div style={{ position: 'relative' }}>
                  <Lock style={{ position: 'absolute', left: '14px', top: '50%', transform: 'translateY(-50%)', color: '#94a3b8' }} size={18} />
                  <input
                    type={showPassword ? "text" : "password"}
                    required
                    style={{ width: '100%', padding: '14px 44px 14px 44px', borderRadius: '12px', border: '1.5px solid #e2e8f0', fontSize: '15px' }}
                    placeholder="••••••••"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                  />
                  <button
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                    style={{
                      position: 'absolute',
                      right: '14px',
                      top: '50%',
                      transform: 'translateY(-50%)',
                      background: 'none',
                      border: 'none',
                      cursor: 'pointer',
                      color: '#94a3b8',
                      display: 'flex',
                      alignItems: 'center',
                      padding: '0'
                    }}
                  >
                    {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                  </button>
                </div>
              </div>
              <button disabled={loading} type="submit" className="auth-btn" style={{ width: '100%', padding: '14px', borderRadius: '12px', background: '#10b981', color: 'white', border: 'none', fontWeight: '700', cursor: 'pointer', display: 'flex', justifyContent: 'center', alignItems: 'center', gap: '10px' }}>
                {loading ? <Loader2 className="animate-spin" /> : 'Reset My Password'}
              </button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
};

export default ForgotPassword;
