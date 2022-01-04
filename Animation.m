% Title:          Simulation of transformation optics designed metamaterial for electromagnetic cloaking
% Author:         Koppány Körmöczi
%-------------------------------------------------------------------------%
% Description:    This is a Matlab code to animate the electromagnetic
%                 cloaking. After the exportation of the mesh data and
%                 solution vector, you can play the animation of the wave
%                 propagation.
%-------------------------------------------------------------------------%



nf=30; % number of frames in one period (default:30)
h=newplot; hf=get(h,'Parent'); set(hf,'Renderer','zbuffer')
axis tight, set(gca,'DataAspectRatio',[1 1 1]); % GCA=Get handle for Current Axis
axis off %
Ms=moviein(nf,hf);

maxu=max(real(u)); % for scaling the colormap
minu=min(real(u)); % for scaling the colormap
colormap(jet);

for n=1:nf
    re_u=real(u*exp(-1i*2*pi*n/nf)); % calculation of time-variation
    pdeplot(p,e,t,'xydata',re_u,'colorbar','on','mesh','off'); % the colormap and/or the mesh can be plotted
    colormap(jet);
    caxis([minu, maxu]); % for scaling the colormap
    axis tight, set(gca,'DataAspectRatio',[1 1 1]); axis off
    Ms(:,n)=getframe;
end

movie(hf,Ms,10); % number of the periods,  (default:10)